/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.wizeline.maven.learningjava.model.BankAccountDTO;

/**
 * Create the BankAccountJob.
 */
@Configuration
public class BankAccountJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * Crea el job y específica steps y listener.
     * @return bankAccountsBackupJob
     */
    @Bean
    public Job bankAccountsBackupJob() {
        return jobBuilderFactory.get("bankAccountsBackupJob")
                .start(bankAccountsBackupStep(stepBuilderFactory))
                .listener(jobExecutionListener())
                .build();

    }

    /**
     * Se define el step y los procesos para leer, procesar y escribir.
     * @param stepBuilderFactory
     * @return bankAccountsBackupStep
     */
    @Bean
    public Step bankAccountsBackupStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("bankAccountsBackupStep")
                .<BankAccountDTO, String>chunk(5).reader(bankAccountsReader())
                .processor(bankAccountItemProcessor()).writer(bankAccountsWriter()).build();
    }


    /**
     * Define un itemReader para leer un archivo csv y mapear el contenido usando BankAccountDTO.
     * @return
     */
    @Bean
    public FlatFileItemReader<BankAccountDTO> bankAccountsReader() {
        return new FlatFileItemReaderBuilder<BankAccountDTO>()
                .name("bankAccountsReader")
                .resource(new ClassPathResource("csv/accounts.csv"))
                .delimited().names(new String[] {"country", "accountName", "accountType", "accountBalance", "userName"})
                .targetType(BankAccountDTO.class).build();
    }

    /**
     * Define un itemWriter para escribir en un archivo txt.
     * @return
     */
    @Bean
    public FlatFileItemWriter<String> bankAccountsWriter() {
        return new FlatFileItemWriterBuilder<String>()
                .name("bankAccountsWriter")
                .resource(new FileSystemResource(
                        "target/test-outputs/bankAccountsBackup.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    @Bean
    public BankAccountItemProcessor bankAccountItemProcessor() {
        return new BankAccountItemProcessor();
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new BatchJobCompletionListener();
    }

}

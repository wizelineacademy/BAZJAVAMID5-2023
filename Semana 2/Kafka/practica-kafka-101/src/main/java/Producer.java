import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws InterruptedException {
        String bootstrapServers = "127.0.0.1:9092";

        // Creating Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // properties.setProperty("bootstrap.servers", bootstrapServers);

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // properties.setProperty("key.serializer", StringSerializer.class.getName());

        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // properties.setProperty("value.serializer", StringSerializer.class.getName());

        // Creating Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // create a producer record
        for(int i=0; i<10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("topic_baz_1", "hello world! " + i);
            Thread.sleep(2000);
            producer.send(record);
        }

        producer.flush(); // wait for data to send the data
        producer.close(); // flush and close producer

    }
}

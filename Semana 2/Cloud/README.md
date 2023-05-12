# Cloud
Recursos de estudio de la sesión en vivo

## Pre-requisitos de la sesión en vivo
- Tener una cuenta de AWS previamente creada(este proceso puede tardar hasta 24 horas de validación).
- Tener el CLI de AWS instalado junto con tus credenciales ingresados
- Tener Java 11 o posterior instalado y Apache Maven.

### Recursos
- [¿Cómo creo y activo una nueva cuenta de AWS?](https://aws.amazon.com/es/premiumsupport/knowledge-center/create-and-activate-aws-account/) (Lectura en foro de AWS).
- [Como crear una cuenta de AWS gratis](https://rubenjgarcia.cloud/crear-cuenta-aws-gratis/) (Video).
- [Como crear una cuenta de AWS](https://kratem.net/como-crear-una-cuenta-de-aws/) (Lectura).
- [Instalacion / actualizacion de la consolsa AWS CLI](https://docs.aws.amazon.com/es_es/cli/latest/userguide/getting-started-install.html) (Lectura).
- [Java SE 11 Archive Downloads](https://www.oracle.com/mx/java/technologies/javase/jdk11-archive-downloads.html) (Descarga)
- [Como instalar Java 11 en Windows](https://www.ionos.es/digitalguide/servidores/configuracion/instalar-java-en-windows-11/)
- [Como instalar Maven](https://www.arteco-consulting.com/post/instalacion-de-maven)

## Temario
1. ¿Qué es cómputo en la nube?
2. Serverles 101
3. AWS Lambdas
4. Practica AWS Lambdas con S3 
5. AWS Cloudwatch
6. Pricing
7. Contenido extra

### 1. ¿Qué es cómputo en la nube?
Prestación de servicios informáticos -incluidos servidores, almacenamiento, bases de datos, redes, software, análisis e inteligencia- a través de Internet ("la nube") para ofrecer una innovación más rápida, recursos flexibles y economías de escala.

#### Modelos de cómputo en la nube
Las soluciones en la nube pueden desplegarse en tres modelos de servicio diferentes, conocidos como Software como Servicio (SaaS), Plataforma como Servicio (PaaS), e Infraestructura como Servicio (IaaS).

`Software como Servicio (SaaS)`: cuenta con aplicaciones que se ofrecen a través de la web, a las cuales se accede a por medio de un navegador y no son administradas por la compañía sino por el proveedor de la aplicación

`La Plataforma como Servicio (PaaS)`: capa intermedia entre la Infraestructura como Servicio (IaaS) y el Software como Servicio (SaaS). PaaS ofrece acceso a ambientes en la nube en los cuales los usuarios pueden construir y entregar aplicaciones sin necesidad de instalar y mantener ambientes de desarrollo complejos que normalmente son muy costosos

`La Infraestructura como Servicio (IaaS)`: ofrece una forma estandarizada de adquirir capacidad computacional por demanda a través de la web durante un periodo de tiempo definido. Estos recursos incluyen almacenamiento, redes, procesamiento y hasta servidores completos.

#### Estrategias de deployment
Forma de cambiar o actualizar una aplicación. El objetivo es realizar el cambio sin tiempo de inactividad de forma que el usuario apenas note las mejoras.

- Blue - Green
- A / B
- Recreate
- Canary / Canarias
- Rolling Upgrade

#### Recursos

- [Casos de Éxito](https://www.inesdi.com/blog/cloud-computing-principales-proveedores-y-casos-de-exito/)
- [Cloud Provider Comparisons: AWS vs Azure vs GCP](https://acloudguru.com/videos/cloud-provider-comparisons/cloud-provider-comparisons-aws-vs-azure-vs-gcp?utm_source=google&utm_medium=paid-search&utm_campaign=cloud-transformation&utm_term=ssi-global-acg-core-dsa&utm_content=free-trial&gclid=Cj0KCQjwmN2iBhCrARIsAG_G2i4oWI-kkOhiKStAdYHFrFfKLBxwNEjBzBaATQw3NdpWak_HTdj4jK8aAja7EALw_wcB) (Video. Muy recomendado)
- [¿Qué es Cloud Computing? (Red hat)](https://www.redhat.com/es/topics/cloud)
- [Cómputo en la nube](https://citelia.es/blog/que-es-cloud-computing-y-como-funciona/)
- [Glosario: 4 estrategias de deployment ](https://keepcoding.io/blog/glosario-estrategias-de-despliegue/#:~:text=Las%20estrategias%20de%20despliegue%20se,que%20afecten%20a%20los%20usuarios.)
- [¿Qué son las estrategias de deployment?](https://turingears.com/que-son-las-estrategias-de-despliegue/)
- [Caso de estudio: Thomson Reuters](https://aws.amazon.com/es/solutions/case-studies/thomson-reuters-migration/)

### 2. Serverless 101
Modelo de ejecución en el que el proveedor en la nube (AWS, Azure o Google Cloud) es responsable de ejecutar un fragmento de código mediante la asignación dinámica de los recursos. Y cobrando solo por la cantidad de recursos utilizados para ejecutar el código.

Es la forma de ejecutar una aplicación sin tener que administrar un servidor.
#### Recursos
- Libro - Learning Serverless By Jason Katzer, O'reilly
- [Red Hat - ¿Qué es un PaaS](https://www.redhat.com/es/topics/cloud-computing/what-is-paas)
- [SaaS vs PaaS vs IaaS](https://www.eginnovations.com/blog/saas-vs-paas-vs-iaas-examples-differences-how-to-choose)
- [Datadog - State of serverless 2021](https://www.datadoghq.com/state-of-serverless-2021)
- [AWS Casos de estudio](https://aws.amazon.com/es/solutions/case-studies/unam/?did=cr_card&trk=cr_card)

### 3. AWS Lambda
Es un enfoque diferente al tradicional basado en servidores físicos o virtuales. Solo se necesita proporcionar la lógica, agrupada en funciones y el propio servicio de AWS Lambda se encarga de ejecutar, administrar el entorno y escalar para mantener el rendimiento.

Las funciones se ejecutan en contenedores donde Lambda tiene el control de los recursos físicos y lógicos bajo el principio de responsabilidad compartida.
#### Recursos
- Libro - AWS Lambda in action By Danilo Poccia, O`reilly
- [Lambda Developer Guide](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)

### 4. Practica AWS Lambdas con S3
Aqui podras encontrar una guia paso por paso para hacer la practica de thumbnails vista durante la clase en vivo.

- [Tutorial paso por paso en AWS](https://docs.aws.amazon.com/es_es/lambda/latest/dg/with-s3-tutorial.html#with-s3-tutorial-prepare)

### 5. AWS Cloudwatch
Monitorea los recursos de AWS y las aplicaciones que se ejecutan en tiempo real, se puede utilizar para recopilar y realizar seguimientos de métricas para medir recursos y aplicaciones.

Dentro de AWS Cloudwatch se pueden realizar las siguientes acciones:
- Crear alarmas de métricas
- Dashboards
- Almacenar logs
#### Recursos
- Libro - Infrastructure Monitoring with Amazon CloudWatch by Ewere Diagboya, O'reilly
- [Cloudwatch User Guide](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/index.html)

###  6. Pricing
AWS ofrece un sistema de pago por uso para los precios de la gran mayoría de los servicios de nube. Con AWS solo paga por los servicios individuales que necesita durante el tiempo que los utilice, sin contratos a largo plazo ni licencias complejas. Los precios de AWS son similares a las tarifas de los servicios de agua y electricidad. Solo paga por lo que consume y, una vez que cancela el servicio, no se aplican costos adicionales ni cuotas de cancelación.
#### Recursos
- [Calculadora AWS](https://calculator.aws/#/)
- [Move hardcoded secrets to AWS Secrets Manager](https://docs.aws.amazon.com/secretsmanager/latest/userguide/hardcoded.html)

### 7. Contenido extra
- [AWS Online Tech Talks](https://aws.amazon.com/events/online-tech-talks/on-demand/?ott-on-demand-all.sort-by=item.additionalFields.startDateTime&ott-on-demand-all.sort-order=desc&awsf.ott-on-demand-master-level=*all&awsf.ott-on-demand-master-category=*all&awsf.ott-on-demand-master-format=*all)
- [AWS Documentation](https://docs.aws.amazon.com)
- [AWS Digital Training](https://aws.amazon.com/training/digital/?cta=tctopbanner)
- [Uso de urls pre-firmadas para compartir archivos de S3](https://docs.aws.amazon.com/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html)


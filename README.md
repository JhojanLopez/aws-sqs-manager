# SQS Manager
This project has made using spring boot v3 with [Spring cloud AWS](https://spring.io/projects/spring-cloud-aws) and
[Spring cloud AWS SQS](https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#sqs-integration).

## Use Cases

Some use cases that it has this project are:

- create and delete queues (standard or FIFO)
- listen queue configured in the application.yml

## Notes

- We must configure queue.consumer to listen it 
- For the correct work of application we must put the AWS credentials in application.yml (access and secret key)
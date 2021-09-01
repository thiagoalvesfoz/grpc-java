# Estudos - Comunicação gRPC

---
## Porque utilizar gRPC

O gRPC é uma tecnologia de código aberto e alto de desempenho criado pela Google em 2015 com o objetivo de trafegar o mínimo possível de dados na rede. 
Suas principais características que a tornam tão poderosa é a capacidade de comunicação entre diversos sistemas através da rede utilizando o HTTP/2 e protocol buffers.

### Os principais benefícios do gRPC são:

- Estrutura RPC leve e de alto desempenho.
- Desenvolvimento de API Contract-first, usando Protocol Buffers por padrão.
- Tem suporte a várias linguagens de programação.
- Opera de forma síncrona e assíncrona.
- Suporta chamadas streaming client, streaming server e streaming bidirecionais.
- Redução do uso de rede através da serialização do Protobuf.
- Pode ser até 6 vezes mais rápido se comparado com JSON
- Muito recomenado para ambientes com arquitetura em microsserviços
- Recomendado para serviços real-time de ponta a ponta.

---

## Como rodar este projeto

![inteface](./doc/cmd-interface.png)

## Requisitos

- JDK 11+
- Maven 3.6
- IDE de sua preferência

## Passo 1
Antes de executar o projeto, é preciso gerar as classes usadas para implementar e consumir serviços gRPC.  
Em um terminal execute:
```bash
mvn clean package
```

## Passo 2
Executar no modo servidor

```bash
java -jar ./target/grpc-1.0-SNAPSHOT-jar-with-dependencies.jar -serve
```

## Passo 3
Executar no modo client

```bash
java -jar ./target/grpc-1.0-SNAPSHOT-jar-with-dependencies.jar -client
```

### Adicional
Por padrão, o servidor executa na porta `50051` como especificado em exemplos da página oficial do gRPC.  
Se você deseja alterar a porta utilizada, utilize a flag `-port`, por exemplo: `-port=50051`.

- Mais informações, digite `-help`


## Links importantes
- Página oficial gRPC: [clique aqui](https://grpc.io/)
- Design Principles: [clique aqui](https://grpc.io/blog/principles/)
- Compilando código gRPC baseando em java: [Clique aqui](https://github.com/grpc/grpc-java)

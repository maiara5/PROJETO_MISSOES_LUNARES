# PROJETO_MISSOES_LUNARES
Este projeto é um sistema de gerenciamento de missões espaciais desenvolvido em Java, utilizando uma arquitetura baseada em Modelo-Serviço-Repositório. O sistema permite cadastrar, listar e visualizar detalhes de missões, astronautas e naves designadas, operando através de uma interface de linha de comando (CLI)

## Funcionalidades Principais
- Cadastro de Missões: Criação de novas missões com código, nome, datas (lançamento e retorno), destino, objetivo e nave associada.
- Gestão de Datas: Validação rigorosa garantindo que a data de retorno seja igual ou posterior à data de lançamento.
- Cálculo de Duração: Calcula a duração total da missão em dias.
- Alocação de Nave: Associa cada missão a um tipo de nave (Tripulada ou Cargueira).
- Gerenciamento de Tripulação: Adição de astronautas à missão com validação de elegibilidade (idade mínima de 21 anos) e verificação da capacidade máxima da nave.
- Persistência Híbrida: Os dados das missões são armazenados em duas abordagens para redundância e diferentes necessidades de acesso:
  - Banco de Dados Nitrite: Utilizado para operações de busca e listagem.
  - Serialização Java: Os dados são salvos em arquivos .bin no sistema de arquivos para um armazenamento persistente.

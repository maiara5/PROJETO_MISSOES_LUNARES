# PROJETO_MISSOES_LUNARES Realizado nas disciplina de Algoritmos e Programação pelas alunas: Maiara Marques Ferreira, Gabrielle Miyuki Saito e Isadora Moreira Theobaldo. #

Este projeto é um sistema de gerenciamento de missões espaciais desenvolvido em Java, utilizando uma arquitetura baseada em Modelo-Serviço-Repositório. O sistema permite cadastrar, listar e visualizar detalhes de missões, astronautas e naves designadas, operando através de uma interface de linha de comando. Utilizando **Maven** para gestão de dependências e compilação, e implementa persistência de dados através de **Serialização Binária** e **NitriteDB**.


## Funcionalidades Principais
- Cadastro de Missões: Criação de novas missões com código, nome, datas (lançamento e retorno), destino, objetivo e nave associada.
- Gestão de Datas: Validação rigorosa garantindo que a data de retorno seja igual ou posterior à data de lançamento.
- Cálculo de Duração: Calcula a duração total da missão em dias.
- Alocação de Nave: Associa cada missão a um tipo de nave (Tripulada ou Cargueira).
- Gerenciamento de Tripulação: Adição de astronautas à missão com validação de elegibilidade (idade mínima de 21 anos) e verificação da capacidade máxima da nave.
- Persistência Híbrida: Os dados das missões são armazenados em duas abordagens para redundância e diferentes necessidades de acesso:
  - Banco de Dados Nitrite: Utilizado para operações de busca e listagem.
  - Serialização Java: Os dados são salvos em arquivos .bin no sistema de arquivos para um armazenamento persistente.
 
  
## 📦 Requisitos do Sistema

Certifique-se de ter as seguintes ferramentas instaladas:

* **Java 17**
* **Apache Maven 3.8+**


## Compilção e Execução:

- Execução via VS Code (Ambiente Recomendado)
- Baixe o Projeto: Baixe o arquivo ZIP do repositório ou clone-o.
- Abra no VS Code: Use File > Open Folder... e selecione a pasta raiz do projeto.
- Execute o main():
- Navegue até o arquivo principal que contém o método main() (provavelmente em src/main/java/...).
- Localize o botão "Run" (ou "Run Java") que aparece acima da declaração do método main().
- Clique em "Run Java" para iniciar a aplicação diretamente no terminal integrado do VS Code.











# Clínica Care — Spring Boot + Thymeleaf

## O que foi adicionado
- Dashboard em `/` com contagem de pacientes, responsáveis, consultas e usuários.
- CRUD web (listar, cadastrar, editar e excluir) para pacientes, responsáveis, consultas e usuários.
- Layout responsivo em `static/css/style.css` e menu compartilhado em `templates/fragments.html`.
- Controllers MVC separados dos controllers REST que já existiam.

## Como executar
1. Tenha Java 17+ e MySQL instalados.
2. Crie o banco: `CREATE DATABASE clinica;`
3. Ajuste as variáveis de ambiente `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`, ou edite `application.properties`.
4. Abra o projeto Maven na IDE e atualize as dependências.
5. Execute `ProvaApplication` e abra `http://localhost:8080/`.

O Hibernate está configurado com `ddl-auto=update`, que cria/atualiza tabelas das entidades. Para um projeto real, prefira migrações versionadas.

## Como o código funciona

### 1. Model (`model/`)
Cada classe anotada com `@Entity` representa uma tabela no banco. `@Id` define a chave primária e `@GeneratedValue` permite que o banco gere o identificador. Os campos usam nomes Java convencionais (`nome`, `cpf`, etc.). Lombok gera getters e setters.

### 2. Repository (`repository/`)
As interfaces estendem `JpaRepository<Entidade, Long>`. Isso já fornece operações como `findAll`, `findById`, `save` e `deleteById`, sem escrever SQL manualmente.

### 3. Service (`service/`)
A camada de serviço recebe o repository pelo construtor e concentra as operações de listar, buscar, salvar e excluir. `@Service` faz o Spring registrar a classe para injeção de dependência.

### 4. Controller MVC (`*WebController.java`)
`@Controller` indica que o método retorna uma página. `Model` leva dados do Java para o template. Por exemplo, `model.addAttribute("pacientes", service.listar())` disponibiliza a lista como `${pacientes}` no Thymeleaf. `return "pacientes"` renderiza `templates/pacientes.html`.

- `@GetMapping` exibe listagens ou prepara edição.
- `@PostMapping` recebe envio de formulário.
- `@ModelAttribute` transforma os campos enviados em um objeto Java.
- `redirect:/pacientes` redireciona após salvar, evitando reenviar o formulário ao atualizar a página.

### 5. Thymeleaf (`templates/`)
- `th:each` percorre uma lista e cria uma linha por registro.
- `th:text` coloca valores do objeto no HTML.
- `th:field` conecta um campo do formulário a uma propriedade Java.
- `th:href` e `th:action` montam URLs usando o contexto da aplicação.
- `fragments.html` contém o menu reutilizado por todas as páginas.

### 6. CSS (`static/css/style.css`)
O Spring serve arquivos de `static` diretamente. O HTML importa o CSS usando `th:href="@{/css/style.css}"`. As media queries adaptam o layout a telas menores.

## Atenções importantes
- A configuração de segurança atual libera as rotas e desativa CSRF apenas para facilitar o teste local de um projeto acadêmico. Não publique assim; configure login, permissões e CSRF antes de produção.
- O campo de senha está mostrado somente para demonstrar o CRUD. Em uma aplicação real, nunca armazene senha em texto puro; use `PasswordEncoder` (por exemplo BCrypt) e evite exibir/editar senha em listagens.
- O modelo original não definia relacionamento entre paciente, responsável e consulta. Por isso, as telas mantêm essas entidades separadas em vez de inventar relações que não existem no código original.
- Os controllers REST existentes continuam separados em `/paciente`, `/pai`, `/clinica` e `/usuario`; as telas Thymeleaf usam `/pacientes`, `/responsaveis`, `/consultas` e `/usuarios`.
- Não consegui validar a compilação neste ambiente porque o Maven Wrapper não conseguiu baixar o Maven do repositório. Atualize as dependências na sua IDE e execute `mvn clean compile` para confirmar no seu ambiente.

# 📌 GitHub API  🚀  
Este é um aplicativo Android que consome a API do GitHub para exibir os repositórios mais populares em Kotlin.


## 📌 Funcionalidades Principais  
✅ **Lista de Repositórios Populares na linguagem Kotlin** → Exibição dos repositórios mais bem avaliados do GitHub.  
✅ **Detalhes do Repositório** → Informação sobre estrelas, forks e autor do projeto.  
✅ **Imagens dos Autor** → Carregamento das fotos dos proprietários dos repositórios.  
✅ **Scroll Infinito** → Paginação implementada para facilitar navegação em listas grandes.  
  

---

## ⚙️ Tecnologias Utilizadas  
### 📌 Linguagem e Frameworks  
- **Kotlin** → Linguagem principal do projeto.  
- **Android Jetpack** → Incluindo Paging para suporte a grandes listas de dados.  
- **LiveData** → Utilizado para reatividade no carregamento de dados.
- **DataBinding**  → Elimina findViewById() e melhora a interação entre UI e lógica de negócio
  
### 📌 Rede e API  
- **Retrofit** → Biblioteca para comunicação com a API do GitHub.  
- **OkHttp** → Cliente HTTP otimizado para chamadas de rede.  
- **Coroutines** → Gerenciamento assíncrono para uma interface responsiva.  
- **Gson** → Para conversão de JSON em objetos Kotlin.  

### 📌 Armazenamento Local  
- **Room Database** → Caso necessário para otimização de performance.   

### 📌 Testes  
- **Espresso** → Testes automatizados de UI para garantir a estabilidade do app.
- **Mockk** → Testes unitários
- **RecyclerViewActions** → Para testar interações com a lista de repositórios.  

---

## 📜 Como Executar  
 Clone este repositório em sua máquina local e utilizar a branch master

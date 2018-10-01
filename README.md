# Desafio IDwall - iddog

## Instalação 
[Download aqui](https://github.com/gustinoco/iddog/blob/develop/IDDog-Tinoco.apk) ou clone o repositório na sua máquina e faça checkout na branch . Faça o build da aplicação utilizando Android Studio

## Arquitetura

Este aplicativo para Android foi criado usando o Kotlin. Kotlin é a mais nova linguagem oficial de programação para aplicativos Android em que facilmente melhora a legibilidade e limitar a verbosidade.

Foi utilizada a [arquitura MVP em Kotlin](https://github.com/googlesamples/android-architecture/tree/todo-mvp-kotlin/) onde tem a facilidade de altear ou adicionar recursos facilmente. 


## Desenvolvimento 
Este aplicativo foi desenvolvido pensando na escalabilidade de categorias de cachorros, caso futuramente seja adicionado uma API para listagem de categoria, será só direcionar a listagem que vem de um Array à chamada da API.
Também foi pensado no consumo de rede. Onde é feito um cacheamento para miniatura da lista. 
Foram utilizadas as mais novas tecnologias, como suporte ao AndroidX, Koin para injeção de dependência. 



## Libs e recursos

  * **MVP**, como arquitetura
  * **GIT** Flow, para organização de features implementadas
  * [Koin](https://insert-koin.io/), Para injeção de depêndencias.
  * [RXJava2](https://github.com/ReactiveX/RxJava), arquitetura reativa
  * [Retrofit](https://square.github.io/retrofit/), para requisições HTTP com RXAdapter
  * [Glide](https://github.com/bumptech/glide), para cacheamento e abrir as imagens vindas da API
  * [Paper](https://github.com/pilgr/Paper), para armazenamento do token 
  * [Crashlytics](https://fabric.io/kits/android/crashlytics), para análise durante o desenvolvimento e posterior.


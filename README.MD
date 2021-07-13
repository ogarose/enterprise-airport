![Deploy](https://github.com/ogarose/enterprise-airport/actions/workflows/aws_production_deploy.yml/badge.svg)
![Build check](https://github.com/ogarose/enterprise-airport/actions/workflows/gradle_build.yml/badge.svg)


## Airport Enterprise Application
**Created during the course "Development Enterprise Applications"**

---
It is multimodule project. 

- mainapp - the main service of the application.

## Project workflow

The project is based on Trunk based development.  

## Initial setup
- run initialSetup.sh

## CI / CD

The project is hosted by AWS. Each New tag raises deployment to production.

During the build next plugins Analyze the code

- Checkstyle - Checks Google Code Style with customizations
- Owasp dependency check - Searches vulnerable dependencies
- pmd - Static code analyzer
- Gradle Version Plugin  - Checks does a new version of the dependencies exists.
- Jococo - minimum test coverage is 50% for now 

General plugins are placed here buildSrc/src/main/kotlin/airport.java-common-conventions.gradle.kts

###Run a new Production deployment
For the deployment we use Gradle Plugin https://github.com/researchgate/gradle-release. 

Switch to master branch and run next command.
```bash
./gradlew :release
```

Steps of the deployment:

- Create a new tag
- Push to github
- Github runs deployment to prod
- Build the project with all Analyzers 
- Build image and push to ECR mainapp  (scripts buildImage.sh and then pushImages.sh)
- AWS ECS runs deployment for the cluster airport. 

### Helpers 

#### Show the list of the update candidate dependencies
The task: dependencyUpdates
The task runs as a dependency for :check command, as the result it runs every build as well.

To check common gradle plugin dependencies run the script checkAllDependencies.sh.  

#### Checkstyle of the code
The tasks: checkstyleMain and checkstyleTest

#### Run PMD static analyzer
The tasks: pmdMain and pdmTest

#### Check OWASP
The task: dependencyCheckAnalyze

## TODO 
- сделать оптимистическую блокировку при сохранении агрегатов. И описать это в readme.md
- использовать vavr либу вместо domain exception


## Вопросы
- При создании рейса есть инвариант: "ВС в эксплуатации". Для этой проверки у ВС должно быть состояние
  или это сделать через правило? Сделал правило AircraftIsInExploitation.
- В агрегатах храним только id-ки других агрегатов? 
- Билет должны быть свободны, это при форматировании заказа. У билета создать какой-то статус которые изменяется когда заказ создан 
  или какое-то правило должно это проверять? (Вопрос похож как и с ВС)
- Для заказа на билеты нужны пассажиры. Я их сделал как VO. Т.к. нигде больше они не нужны. Но есть подозрения что надо
 было сделать их как entity. flightManagement.Order.Passenger .  


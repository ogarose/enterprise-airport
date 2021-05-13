## Airport Enterprise Application
**Created during the course "Development Enterprise Applications"**

---
It is multimodule project. 

- mainapp - the main service of the application.

## CI / CD

The project is hosted by AWS. Each New tag raises deployment to production.


###Run a new Production deployment
For the deployment we use Gradle Plugin https://github.com/researchgate/gradle-release. 

To run new deployment to prod run command
```bash
gradle :mainapp:release
```

### Helpers 

#### Show the list of update candidate dependencies

Run the task dependencyUpdates

####  


## TODO
1. Need to choose branching workflow.

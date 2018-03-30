# Hystrix Collapser Sample

This Repository is a sample application using HystrixCollpaser and Spring Boot JPA.

### How it works
![Alt text](https://monosnap.com/file/WGENDrEpRWaSnC9jneA73JSzdH8PKf.png)
- `@HystrixCollapser` collapse all request for given duration(`timerDelayInMilliseconds`)
- [Netflix wiki](https://github.com/Netflix/Hystrix/wiki/How-it-Works#RequestCollapsing)

### @HystrixCollapser
```
@HystrixCollapser(scope = GLOBAL,
                      batchMethod = "getProductByIds",
                      collapserProperties = {
                          @HystrixProperty(name = "timerDelayInMilliseconds", value = "5000"), //default 10ms
                          @HystrixProperty(name = "maxRequestsInBatch", value = "10")
                      })
    public Product getProductById(Long id) {
        throw new RuntimeException("This method body should not be executed");
    }
```
#### Scope
- GLOBAL
  - Requests from any thread (ie. all HTTP requests) within the JVM will be collapsed. 1 queue for entire app.
- REQUEST
   - Typically this means that requests within a single user-request (ie. HTTP request) are collapsed. No interaction with other user requests. 1 queue per user request.

#### batchMethod
- should `@HystrixCommand`

#### HystrixProperty [link](https://github.com/Netflix/Hystrix/blob/68251383aa6ca0842ab5597fcf2f26c5a5b77aba/hystrix-core/src/main/java/com/netflix/hystrix/HystrixCollapserProperties.java#L32)
  -  `timerDelayInMilliseconds` :  collpase request within this duration
  -  `maxRequestsInBatch` : max request count per collaping

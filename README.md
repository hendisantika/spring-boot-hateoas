# Spring Boot HATEOAS

### Introduction to HATEOAS With Spring Boot Data Rest

Things to do :

run this in your terminal :

`mvn gradle clean bootRun`

HATEOAS (Hypermedia as the Engine of Application State) specifies that the REST API’s should provide enough information to the client to interact with the server. This is different from the SOA (Service-Oriented Architecture) where a client and a server interact through a fixed contract. We’ll look more into HATEOAS in a while.

Spring Data Rest is built on top of Spring Data, Spring Web MVC & Spring Hateos. It analyzes all the domain models and exposes Hypermedia Driven REST endpoints for them automatically. In the meanwhile, all the features of Spring Data Repositories like sorting, pagination etc. are available in these endpoints.

Let’s check the APIs after we run our project.

`curl 'http://localhost:8080'`

```
{
   "_links": {
       "countries": {
           "href": "http://localhost:8080/countries{?page,size,sort}",
           "templated": true
       },
       "cities": {
           "href": "http://localhost:8080/metropolises{?page,size,sort}",
           "templated": true
       },
       "profile": {
           "href": "http://localhost:8080/profile"
       }
   }
}
```

Let’s add a few Countries.

```
curl 'http://localhost:8080/countries' -X POST -d '{"name":"Japan"}' -H 'Content-Type: application/json'
curl 'http://localhost:8080/countries' -X POST -d '{"name":"India"}' -H 'Content-Type: application/json'
curl 'http://localhost:8080/countries' -X POST -d '{"name":"Germany"}' -H 'Content-Type: application/json'
curl 'http://localhost:8080/countries' -X POST -d '{"name":"Canada"}' -H 'Content-Type: application/json'
curl 'http://localhost:8080/countries' -X POST -d '{"name":"Australia"}' -H 'Content-Type: application/json'
```

Let’s fetch a paginated result of Countries with the results sorted by Country name, the page size 2 and the 1st page.

`curl 'http://localhost:8080/countries/?sort=name,asc&page=1&size=2'`

```
{
    "_embedded": {
        "countries": [
            {
                "name": "Germany",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/countries/3"
                    },
                    "country": {
                        "href": "http://localhost:8080/countries/3"
                    }
                }
            },
            {
                "name": "India",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/countries/2"
                    },
                    "country": {
                        "href": "http://localhost:8080/countries/2"
                    }
                }
            }
        ]
    },
    "_links": {
        "first": {
            "href": "http://localhost:8080/countries?page=0&size=2&sort=name,asc"
        },
        "prev": {
            "href": "http://localhost:8080/countries?page=0&size=2&sort=name,asc"
        },
        "self": {
            "href": "http://localhost:8080/countries"
        },
        "next": {
            "href": "http://localhost:8080/countries?page=2&size=2&sort=name,asc"
        },
        "last": {
            "href": "http://localhost:8080/countries?page=2&size=2&sort=name,asc"
        },
        "profile": {
            "href": "http://localhost:8080/profile/countries"
        }
    },
    "page": {
        "size": 2,
        "totalElements": 5,
        "totalPages": 3,
        "number": 1
    }
}
```

Apart from the expected countries, we also get the links to different pages and further information that might help in handling pagination better.

The links to the first, previous, self, next and last pages can directly be used.

Let’s add a City and associate it with a Country.

`curl 'http://localhost:8080/metropolises' -X POST -d '{"name":"Osaka", "country":"http://localhost:8080/countries/1"}' -H 'Content-Type: application/json'`

We have to pass the url of the country and this will be mapped to Japan. We saved Japan first, hence its id is 1.

Let’s see what we get when we fetch that City.

`curl 'http://localhost:8080/metropolises/1'`

```
{
    "name": "Osaka",
    "_links": {
        "self": {
            "href": "http://localhost:8080/metropolises/1"
        },
        "city": {
            "href": "http://localhost:8080/metropolises/1"
        },
        "country": {
            "href": "http://localhost:8080/metropolises/1/country"
        }
    }
}
```

We are getting a link to the Country associated with it. Let’s see what we get in response for it.

```
{
    "name": "Japan",
    "_links": {
        "self": {
            "href": "http://localhost:8080/countries/1"
        },
        "country": {
            "href": "http://localhost:8080/countries/1"
        }
    }
}
```






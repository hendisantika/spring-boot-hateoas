package com.hendisantika.springboothateoas.repository;

import com.hendisantika.springboothateoas.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-hateoas
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/17
 * Time: 23.36
 * To change this template use File | Settings | File Templates.
 */

@RepositoryRestResource(path = "metropolises")
public interface CityRepository extends JpaRepository<City, Long> {
}

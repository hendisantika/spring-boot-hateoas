package com.hendisantika.springboothateoas.repository;

import com.hendisantika.springboothateoas.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-hateoas
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/17
 * Time: 23.37
 * To change this template use File | Settings | File Templates.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}

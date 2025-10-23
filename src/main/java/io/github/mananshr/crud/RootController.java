package io.github.mananshr.crud;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/")
@Secured(SecurityRule.IS_ANONYMOUS)
public class RootController {

	@Get(uri="/", produces="text/plain")
	public String index() {
		return "Public root — no authentication required";
	}
}

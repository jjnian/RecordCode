plugins {
	war
	id("org.springframework.boot") version "{gradle-project-version}"
}

// tag::main-class[]
springBoot {
	mainClassName = "com.example.ExampleApplication"
}
// end::main-class[]

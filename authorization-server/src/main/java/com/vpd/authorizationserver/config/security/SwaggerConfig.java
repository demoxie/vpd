package com.vpd.authorizationserver.config.security;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Authorization server Application",
                description = "APIs for Authorization server Application",
                version = "1.0",
                contact = @Contact(
                        name = "VPD",
                        email = "shadrachadamuul@gmail.com",
                        url = "https://github.com/abner-vee"
                ),
                license = @License(
                        name = "Authorization server Application",
                        url = "https://github.com/demoxie"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Authorization server application",
                url = "https://github.com/demoxie"
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}

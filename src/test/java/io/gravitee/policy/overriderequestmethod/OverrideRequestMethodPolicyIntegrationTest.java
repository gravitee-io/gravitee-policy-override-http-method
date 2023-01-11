/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.policy.overriderequestmethod;

import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import io.gravitee.apim.gateway.tests.sdk.AbstractPolicyTest;
import io.gravitee.apim.gateway.tests.sdk.annotations.DeployApi;
import io.gravitee.apim.gateway.tests.sdk.annotations.GatewayTest;
import io.gravitee.apim.gateway.tests.sdk.configuration.GatewayConfigurationBuilder;
import io.gravitee.policy.overriderequestmethod.configuration.OverrideRequestMethodPolicyConfiguration;
import io.gravitee.policy.v3.overriderequestmethod.OverrideRequestMethodPolicyV3;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava3.core.http.HttpClient;
import io.vertx.rxjava3.core.http.HttpClientRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Yann TAVERNIER (yann.tavernier at graviteesource.com)
 * @author GraviteeSource Team
 */
@GatewayTest
@DeployApi("/apis/v3_definition/override-http-method.json")
public class OverrideRequestMethodPolicyIntegrationTest
    extends AbstractPolicyTest<OverrideRequestMethodPolicyV3, OverrideRequestMethodPolicyConfiguration> {

    @Override
    protected void configureGateway(GatewayConfigurationBuilder gatewayConfigurationBuilder) {
        super.configureGateway(gatewayConfigurationBuilder);
        gatewayConfigurationBuilder.set("api.jupiterMode.enabled", "true");
        gatewayConfigurationBuilder.set("api.jupiterMode.default", "always");
    }

    @ParameterizedTest(name = "Should change method {0} to GET")
    @ValueSource(strings = { "POST", "DELETE", "PATCH", "HEAD" })
    void shouldChangeMethod(String method, HttpClient client) throws InterruptedException {
        wiremock.stubFor(get("/endpoint").willReturn(ok()));

        client
            .rxRequest(HttpMethod.valueOf(method), "/test")
            .flatMap(HttpClientRequest::rxSend)
            .test()
            .await()
            .assertValue(response -> {
                assertThat(response.statusCode()).isEqualTo(200);
                return true;
            })
            .assertComplete()
            .assertNoErrors();

        wiremock.verify(exactly(1), getRequestedFor(urlEqualTo("/endpoint")));
    }
}

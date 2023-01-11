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
package io.gravitee.policy.overriderequestmethod.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gravitee.common.http.HttpMethod;
import java.io.IOException;
import java.net.URL;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class OverrideRequestMethodPolicyConfigurationTest {

    @Test
    public void shouldOverrideRequestMethod() throws IOException {
        OverrideRequestMethodPolicyConfiguration configuration = load(
            "/io/gravitee/policy/overriderequestmethod/configuration/overriderequestmethod01.json",
            OverrideRequestMethodPolicyConfiguration.class
        );

        Assertions.assertThat(configuration).isNotNull();
        Assertions.assertThat(configuration.getMethod()).isEqualTo(HttpMethod.PUT);
    }

    private <T> T load(String resource, Class<T> type) throws IOException {
        URL jsonFile = this.getClass().getResource(resource);
        return new ObjectMapper().readValue(jsonFile, type);
    }
}

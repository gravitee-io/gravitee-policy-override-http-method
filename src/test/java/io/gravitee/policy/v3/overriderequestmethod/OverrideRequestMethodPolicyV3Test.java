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
package io.gravitee.policy.v3.overriderequestmethod;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import io.gravitee.common.http.HttpMethod;
import io.gravitee.gateway.api.ExecutionContext;
import io.gravitee.gateway.api.Request;
import io.gravitee.gateway.api.Response;
import io.gravitee.policy.api.PolicyChain;
import io.gravitee.policy.overriderequestmethod.configuration.OverrideRequestMethodPolicyConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Yann TAVERNIER (yann.tavernier at graviteesource.com)
 * @author GraviteeSource Team
 */
@ExtendWith(MockitoExtension.class)
class OverrideRequestMethodPolicyV3Test {

    private OverrideRequestMethodPolicyV3 policy;

    @Mock
    private OverrideRequestMethodPolicyConfiguration configuration;

    @Mock
    private Request request;

    @Mock
    private Response response;

    @Mock
    private ExecutionContext executionContext;

    @Mock
    protected PolicyChain policyChain;

    @BeforeEach
    public void init() {
        initMocks(this);

        policy = new OverrideRequestMethodPolicyV3(configuration);
    }

    @Test
    public void test_override_http_method() {
        when(configuration.getMethod()).thenReturn(HttpMethod.PUT);

        policy.onRequest(request, response, executionContext, policyChain);

        verify(executionContext).setAttribute(ExecutionContext.ATTR_REQUEST_METHOD, configuration.getMethod());
        verify(policyChain).doNext(request, response);
    }
}

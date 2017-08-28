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

import io.gravitee.common.http.HttpMethod;
import io.gravitee.gateway.api.ExecutionContext;
import io.gravitee.gateway.api.Request;
import io.gravitee.gateway.api.Response;
import io.gravitee.policy.api.PolicyChain;
import io.gravitee.policy.overriderequestmethod.configuration.OverrideRequestMethodPolicyConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
@RunWith(MockitoJUnitRunner.class)
public class OverrideRequestMethodPolicyTest {

    private OverrideRequestMethodPolicy policy;

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

    @Before
    public void init() {
        initMocks(this);

        policy = new OverrideRequestMethodPolicy(configuration);
    }

    @Test
    public void test_override_http_method() {
        when(configuration.getMethod()).thenReturn(HttpMethod.PUT);

        policy.onRequest(request, response, executionContext, policyChain);

        verify(executionContext).setAttribute(ExecutionContext.ATTR_REQUEST_METHOD, configuration.getMethod());
        verify(policyChain).doNext(request, response);
    }
}

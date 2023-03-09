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
import io.gravitee.gateway.reactive.api.context.ContextAttributes;
import io.gravitee.gateway.reactive.api.context.HttpExecutionContext;
import io.gravitee.gateway.reactive.api.policy.Policy;
import io.gravitee.policy.overriderequestmethod.configuration.OverrideRequestMethodPolicyConfiguration;
import io.gravitee.policy.v3.overriderequestmethod.OverrideRequestMethodPolicyV3;
import io.reactivex.rxjava3.core.Completable;

/**
 * @author Yann TAVERNIER (yann.tavernier at graviteesource.com)
 * @author GraviteeSource Team
 */
public class OverrideRequestMethodPolicy extends OverrideRequestMethodPolicyV3 implements Policy {

    private final HttpMethod configuredMethod;

    /**
     * Create a new policy instance based on its associated configuration
     *
     * @param configuration the associated configuration to the new policy instance
     */
    public OverrideRequestMethodPolicy(OverrideRequestMethodPolicyConfiguration configuration) {
        super(configuration);
        configuredMethod = configuration.getMethod();
    }

    @Override
    public String id() {
        return "policy-override-request-method";
    }

    @Override
    public Completable onRequest(HttpExecutionContext ctx) {
        return Completable.fromRunnable(() -> {
            ctx.setAttribute(ContextAttributes.ATTR_REQUEST_METHOD, configuredMethod);
            ctx.request().method(configuredMethod);
        });
    }
}

/*
 * Copyright 2012-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.elasticsearch.rest;

import org.elasticsearch.client.RestClientBuilder;

/**
 * Callback interface that can be implemented by beans wishing to further customize the
 * {@link org.elasticsearch.client.RestClient} via a {@link RestClientBuilder} whilst
 * retaining default auto-configuration.
 *
 * @author Brian Clozel
 * @since 2.1.0
 * @deprecated since 2.3.1 for removal in 2.5 in favor of
 * {@link org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer}
 */
@FunctionalInterface
@Deprecated
public interface RestClientBuilderCustomizer
		extends org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer {

}

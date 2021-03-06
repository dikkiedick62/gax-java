/*
 * Copyright 2017, Google LLC All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google LLC nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.auth.Credentials;
import org.threeten.bp.Duration;

/**
 * Context for an API call.
 *
 * <p>Implementations need to be immutable because default instances are stored in callable objects.
 *
 * <p>This is transport specific and each transport has an implementation with its own options.
 */
@InternalExtensionOnly
public interface ApiCallContext {

  /** Returns a new ApiCallContext with the given credentials set. */
  ApiCallContext withCredentials(Credentials credentials);

  /** Returns a new ApiCallContext with the given channel set. */
  ApiCallContext withTransportChannel(TransportChannel channel);

  /**
   * Returns a new ApiCallContext with the given timeout set.
   *
   * <p>This timeout only applies to a single RPC call; if timeouts are configured, the overall time
   * taken will be much higher.
   */
  ApiCallContext withTimeout(Duration rpcTimeout);

  /** If inputContext is not null, returns it; if it is null, returns the present instance. */
  ApiCallContext nullToSelf(ApiCallContext inputContext);

  /**
   * For any values in {@code inputCallContext} that are not null, override the corresponding values
   * in the present instance.
   */
  ApiCallContext merge(ApiCallContext inputCallContext);
}

/*
 * Copyright © 2012-2014 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.cask.coopr.shell.command;

import co.cask.common.cli.Arguments;
import co.cask.common.cli.Command;
import co.cask.coopr.client.ClusterClient;
import co.cask.coopr.http.request.ClusterOperationRequest;
import co.cask.coopr.shell.util.CliUtil;

import java.io.PrintStream;
import com.google.inject.Inject;

import static co.cask.coopr.shell.util.Constants.CLUSTER_ID_KEY;
import static co.cask.coopr.shell.util.Constants.PROVIDER_FIELDS_KEY;
import static co.cask.coopr.shell.util.Constants.SERVICE_ID_KEY;

/**
 * Starts service on cluster.
 */
public class StartServiceOnClusterCommand implements Command {

  private final ClusterClient clusterClient;

  @Inject
  private StartServiceOnClusterCommand(ClusterClient clusterClient) {
    this.clusterClient = clusterClient;
  }

  @Override
  public void execute(Arguments arguments, PrintStream printStream) throws Exception {
    String clusterId = CliUtil.checkArgument(arguments.get(CLUSTER_ID_KEY));
    String serviceId = CliUtil.checkArgument(arguments.get(SERVICE_ID_KEY));
    ClusterOperationRequest clusterOperationRequest = CliUtil.getObjectFromJson(arguments, PROVIDER_FIELDS_KEY,
                                                                                ClusterOperationRequest.class);
    if (clusterOperationRequest == null) {
      clusterClient.startServiceOnCluster(clusterId, serviceId);
    } else {
      clusterClient.startServiceOnCluster(clusterId, serviceId, clusterOperationRequest);
    }
  }

  @Override
  public String getPattern() {
    return String.format("start service <%s> on cluster <%s> [with provider fields <%s>]",
                         SERVICE_ID_KEY, CLUSTER_ID_KEY, PROVIDER_FIELDS_KEY);
  }

  @Override
  public String getDescription() {
    return "Starts service on cluster";
  }
}

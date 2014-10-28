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

package co.cask.coopr.shell.command.set;

import co.cask.common.cli.Command;
import co.cask.coopr.shell.command.AddServicesOnClusterCommand;
import co.cask.coopr.shell.command.ConnectCommand;
import co.cask.coopr.shell.command.CreateClusterCommand;
import co.cask.coopr.shell.command.DeleteAutomatorTypeResourcesCommand;
import co.cask.coopr.shell.command.DeleteClusterCommand;
import co.cask.coopr.shell.command.DeleteClusterTemplateCommand;
import co.cask.coopr.shell.command.DeleteHardwareTypeCommand;
import co.cask.coopr.shell.command.DeleteImageTypeCommand;
import co.cask.coopr.shell.command.DeleteProviderCommand;
import co.cask.coopr.shell.command.DeleteProviderTypeResourcesCommand;
import co.cask.coopr.shell.command.DeleteServiceCommand;
import co.cask.coopr.shell.command.DeleteTenantCommand;
import co.cask.coopr.shell.command.ExitCommand;
import co.cask.coopr.shell.command.GetAutomatorTypeCommand;
import co.cask.coopr.shell.command.GetClusterCommand;
import co.cask.coopr.shell.command.GetClusterConfigCommand;
import co.cask.coopr.shell.command.GetClusterStatusCommand;
import co.cask.coopr.shell.command.GetClusterTemplateCommand;
import co.cask.coopr.shell.command.GetHardwareTypeCommand;
import co.cask.coopr.shell.command.GetImageTypeCommand;
import co.cask.coopr.shell.command.GetProviderCommand;
import co.cask.coopr.shell.command.GetProviderTypeCommand;
import co.cask.coopr.shell.command.GetProvisionerCommand;
import co.cask.coopr.shell.command.GetServiceCommand;
import co.cask.coopr.shell.command.GetTenantCommand;
import co.cask.coopr.shell.command.ListAllAutomatorTypesCommand;
import co.cask.coopr.shell.command.ListAllProviderTypesCommand;
import co.cask.coopr.shell.command.ListAutomatorTypeResourcesCommand;
import co.cask.coopr.shell.command.ListClusterServicesCommand;
import co.cask.coopr.shell.command.ListClusterTemplatesCommand;
import co.cask.coopr.shell.command.ListClustersCommand;
import co.cask.coopr.shell.command.ListHardwareTypesCommand;
import co.cask.coopr.shell.command.ListImageTypesCommand;
import co.cask.coopr.shell.command.ListProviderTypeResourcesCommand;
import co.cask.coopr.shell.command.ListProvidersCommand;
import co.cask.coopr.shell.command.ListProvisionersCommand;
import co.cask.coopr.shell.command.ListServicesCommand;
import co.cask.coopr.shell.command.ListTenantsCommand;
import co.cask.coopr.shell.command.RecallAutomatorTypeResourcesCommand;
import co.cask.coopr.shell.command.RecallProviderTypeResourcesCommand;
import co.cask.coopr.shell.command.RestartServiceOnClusterCommand;
import co.cask.coopr.shell.command.SetClusterConfigCommand;
import co.cask.coopr.shell.command.SetClusterExpireTimeCommand;
import co.cask.coopr.shell.command.StageAutomatorTypeResourcesCommand;
import co.cask.coopr.shell.command.StageProviderTypeResourcesCommand;
import co.cask.coopr.shell.command.StartServiceOnClusterCommand;
import co.cask.coopr.shell.command.StopServiceOnClusterCommand;
import co.cask.coopr.shell.command.SyncClusterTemplateCommand;
import co.cask.coopr.shell.command.SyncPluginCommand;
import com.google.common.collect.ImmutableList;
import com.google.inject.Injector;

import java.util.List;

/**
 * Command set utility class.
 */
public class CommandSet {

  public static co.cask.common.cli.CommandSet<Command> getCliCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(ConnectCommand.class),
      injector.getInstance(ExitCommand.class)
    );
    List<co.cask.common.cli.CommandSet<Command>> commandSets = ImmutableList.of(
      getAdminCommandSet(injector),
      getClusterCommandSet(injector),
      getPluginCommandSet(injector),
      getTenantCommandSet(injector),
      getProvisionerCommandSet(injector)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands, commandSets);
  }

  private static co.cask.common.cli.CommandSet<Command> getAdminCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(DeleteClusterTemplateCommand.class),
      injector.getInstance(DeleteHardwareTypeCommand.class),
      injector.getInstance(DeleteImageTypeCommand.class),
      injector.getInstance(DeleteProviderCommand.class),
      injector.getInstance(DeleteServiceCommand.class),
      injector.getInstance(GetClusterTemplateCommand.class),
      injector.getInstance(GetHardwareTypeCommand.class),
      injector.getInstance(GetImageTypeCommand.class),
      injector.getInstance(GetProviderCommand.class),
      injector.getInstance(GetServiceCommand.class),
      injector.getInstance(ListClusterTemplatesCommand.class),
      injector.getInstance(ListHardwareTypesCommand.class),
      injector.getInstance(ListImageTypesCommand.class),
      injector.getInstance(ListProvidersCommand.class),
      injector.getInstance(ListServicesCommand.class)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands);
  }

  private static co.cask.common.cli.CommandSet<Command> getClusterCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(AddServicesOnClusterCommand.class),
      injector.getInstance(CreateClusterCommand.class),
      injector.getInstance(DeleteClusterCommand.class),
      injector.getInstance(GetClusterCommand.class),
      injector.getInstance(GetClusterConfigCommand.class),
      injector.getInstance(GetClusterStatusCommand.class),
      injector.getInstance(ListClustersCommand.class),
      injector.getInstance(ListClusterServicesCommand.class),
      injector.getInstance(RestartServiceOnClusterCommand.class),
      injector.getInstance(SetClusterConfigCommand.class),
      injector.getInstance(SetClusterExpireTimeCommand.class),
      injector.getInstance(StartServiceOnClusterCommand.class),
      injector.getInstance(StopServiceOnClusterCommand.class),
      injector.getInstance(SyncClusterTemplateCommand.class)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands);
  }

  private static co.cask.common.cli.CommandSet<Command> getPluginCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(DeleteAutomatorTypeResourcesCommand.class),
      injector.getInstance(DeleteProviderTypeResourcesCommand.class),
      injector.getInstance(GetAutomatorTypeCommand.class),
      injector.getInstance(GetProviderTypeCommand.class),
      injector.getInstance(ListAllAutomatorTypesCommand.class),
      injector.getInstance(ListAllProviderTypesCommand.class),
      injector.getInstance(ListAutomatorTypeResourcesCommand.class),
      injector.getInstance(ListProviderTypeResourcesCommand.class),
      injector.getInstance(RecallAutomatorTypeResourcesCommand.class),
      injector.getInstance(RecallProviderTypeResourcesCommand.class),
      injector.getInstance(StageAutomatorTypeResourcesCommand.class),
      injector.getInstance(StageProviderTypeResourcesCommand.class),
      injector.getInstance(SyncPluginCommand.class)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands);
  }

  private static co.cask.common.cli.CommandSet<Command> getTenantCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(DeleteTenantCommand.class),
      injector.getInstance(GetTenantCommand.class),
      injector.getInstance(ListTenantsCommand.class)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands);
  }

  private static co.cask.common.cli.CommandSet<Command> getProvisionerCommandSet(Injector injector) {
    List<Command> commands = ImmutableList.of(
      injector.getInstance(GetProvisionerCommand.class),
      injector.getInstance(ListProvisionersCommand.class)
    );
    return new co.cask.common.cli.CommandSet<Command>(commands);
  }
}

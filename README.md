# Distributed Railway Control
This repository contains five different UPPAAL models and a UMC model of an existing distributed railway control system algorithm (RELIS 2000 system of INSY GmbH). The purpose of this work is
1. to investigate the modelling the system with respect to the required operations for a
railway control system, and 
2. to model check the safety requirements (No derailment and No collision) and the operational correctness (see chapter 7 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf) for the complete list). 

The different models are as follows:
* RCS: Contains the basic version of the distributed railway control system with the following operations (defined using UPPAAL):
  * Reserve a railway segment
  * Lock a railway switch
  * Move between railway segments
  The full description of this model can be found in chapter 6 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)
* RCS-Restricted: The same basic version as RCS, but with a stricter operation order for trains. In RCS a train can make various interleavings of the given operations, but this is restricted to the following operational sequence
  * Reserve as many segment as possible
  * Lock as many railway switches as possible
  * Move through between fully reserved and locked railway segments
  The full description can be found in chapter 9.1 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)
* RCS-Extended: The variant extends RCS with a Cancel operation that removes locks and reservation at a given railway switch. See chapter 9.2 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)
* RCS-Global: Identical to RCS with the only difference being that each template uses the global definition of the railway network instead of copying the relevant parts into their local state. See chapter 9.3 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)
* RCS-UMC: This is a translation of RCS into a UMC model where the uses of UPPAAL channels are converted into UMC signals. The full description is available in chapter 10 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)
* RCS-UMCCompatible: This is a translation of RCS-UMC back into UMC, with the primary purpose of investigating the modelling techniques impact on the verification time. The full description is available in chapter 11 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)

## Checking the correctness of the models
The folder [Check Configurations and Results](https://github.com/perlangelaursen/DistributedRailwayControl/tree/master/Check%20Configurations%20and%20Results) contains the various checks and results that have been performed on all models. Each check has used a minimal railway network that encapsulates the situation that the property is meant to hold. There are 17 checks in total and each check contains the relevant UPPAAL/UMC query for the property as well as the actual result. The complete list of checks can be found in chapter 7 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)).

## Verification Experiments
The folder [Experiment Configurations](https://github.com/perlangelaursen/DistributedRailwayControl/tree/master/Experiment%20Configurations) contains the various configurations that have been used to determine the scalablity of each of the different models in terms of how many trains and segments it can handle before a state space explosion using more theorectical railway network. Each of the models has been executed with an actual railway Nærumbanen (located in the Greater Copenhagen area) with two different configurations (normal traffic and rushhour traffic). Each configuration has been executed three times in order to minimize the result variation. The list of configurations along with the specific UPPAAL/UMC parameters can be found in chapter 13 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)). The complete set of results can be found in the appendix of [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)) and in [RCSCompleteResults.org](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/RCSCompleteResults.org).

## Generating railway model instances
The final three folders [Temporary](https://github.com/perlangelaursen/DistributedRailwayControl/tree/master/Temporary), [Thesis-Workspace](https://github.com/perlangelaursen/DistributedRailwayControl/tree/master/Thesis-Workspace) and [runtime-RCS](https://github.com/perlangelaursen/DistributedRailwayControl/tree/master/runtime-RCS) contains an Eclipse Plugin (with usage examples) that can be used to draw railway networks and export all the previously UPPAAL and UMC models. The description of the Eclipse Plugin can be seen in chapter 12 in [s144449s144456-MSc-Thesis.pdf](https://github.com/perlangelaursen/DistributedRailwayControl/blob/master/s144449s144456-MSc-Thesis.pdf)).

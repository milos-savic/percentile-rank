## Setting up the development environment

Install the following:

* Oracle Java SDK 1.8
* Maven 3.3.9 and higher

You can use any IDE that has support for maven.


## Build & start application

See README.md


## Dependencies

All Maven repositories and dependencies should be declared in dependencies/pom.xml. The idea is to have a central place
where dependency versions are specified instead of them being scattered across many pom's.
Any terminal (leaf) maven module should have its parent set to the dependencies module, e.g. for commons module we have:

    <parent>
        <groupId>by.get.pms</groupId>
        <artifactId>dependencies</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../../dependencies/pom.xml</relativePath>
    </parent>

Dependency declarations in all maven modules (other than dependencies) should not contain <version> element.


## Git settings

The following must be set to avoid unnecessary merge commits:

    git config branch.master.rebase true

This should also be set for every branch by replacing master with the branch name.

When this setting is activated for a branch, git adds the `--rebase` argument to the pull command by default, 
which means that `git pull` is not making a fetch+merge, but rather fetch+rebase. 
This means that you won't get the unnecessary merge commits saying "master is merged with master", 
which have no value at all.

To make this the default behavior for new branches (it does not affect branches already created), 
run this command:

    git config branch.autosetuprebase always
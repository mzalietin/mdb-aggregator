package me.mzalietin.imdbproject.movie;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = {
    "me.mzalietin.imdbproject.movie",
})
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
        .domainModels("..core.domain..")
        .domainServices("..core.usecase..")
        .applicationServices("..config..")
        .adapter("eventbus", "..gateway.eventbus..")
        .adapter("persistence", "..gateway.dataprovider..");
}

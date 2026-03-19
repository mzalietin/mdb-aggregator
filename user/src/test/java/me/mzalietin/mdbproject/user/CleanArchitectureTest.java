package me.mzalietin.mdbproject.user;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = {
    "me.mzalietin.mdbproject.user"
})
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule clean_architecture_is_respected = onionArchitecture()
        .withOptionalLayers(true)
        .domainModels("..domain.model..")
        .adapter("repo", "..infrastructure.repo..")
        .adapter("dependencyInjection", JavaClass.Predicates.simpleName("UserContextConfig"));
}

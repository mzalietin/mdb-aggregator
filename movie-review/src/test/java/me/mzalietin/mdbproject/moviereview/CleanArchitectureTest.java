package me.mzalietin.mdbproject.moviereview;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = {
    "me.mzalietin.mdbproject.moviereview"
})
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule clean_architecture_is_respected = onionArchitecture()
        .domainModels("..domain.model..")
        .domainServices("..domain.service..")
        .applicationServices("..application..")
        .adapter("broker", "..infrastructure.broker..")
        .adapter("repo", "..infrastructure.repo..")
        .adapter("rest", "..infrastructure.rest..")
        .adapter("dependencyInjection", JavaClass.Predicates.simpleName("MovieReviewContextConfig"))
        .adapter("archTest", JavaClass.Predicates.simpleName("CleanArchitectureTest"))
        .ensureAllClassesAreContainedInArchitecture();
}

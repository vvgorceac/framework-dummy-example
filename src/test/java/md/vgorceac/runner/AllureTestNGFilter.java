package md.vgorceac.runner;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;
import io.qameta.allure.testfilter.FileTestPlanSupplier;
import io.qameta.allure.testfilter.TestPlan;
import io.qameta.allure.testfilter.TestPlanSupplier;
import io.qameta.allure.testfilter.TestPlanV1_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllureTestNGFilter {

    private static final String ID_NAME = "id";
    private static final Pattern ID_TAG = Pattern.compile("^@?allure\\.id[:=](?<id>.+)$");
    private static final Pattern FEATURE = Pattern.compile("^\"Optional\\[(?<name>.+)]\"$");

    private final TestPlanSupplier supplier;

    public AllureTestNGFilter(final TestPlanSupplier supplier) {
        this.supplier = supplier;
    }

    public AllureTestNGFilter() {
        this(new FileTestPlanSupplier());
    }

    public Object[][] filter(final Object[][] scenarios) {
        final Optional<TestPlan> testPlan = supplier.supply();
        return testPlan.map(plan -> filter(scenarios, (TestPlanV1_0) plan)).orElse(scenarios);
    }

    private Object[][] filter(final Object[][] scenarios, TestPlanV1_0 testPlan) {
        final List<Object[]> result = new ArrayList<>();
        Arrays.stream(scenarios)
                .filter(scenario -> filter(scenario, testPlan))
                .forEach(result::add);
        return result.toArray(new Object[][]{});
    }

    private boolean filter(final Object[] scenario, TestPlanV1_0 testPlan) {
        if (scenario.length != 2) {
            return true;
        }
        if (scenario[0] instanceof PickleWrapper && scenario[1] instanceof FeatureWrapper) {
            final Pickle pickle = ((PickleWrapper) scenario[0]).getPickle();
            final String feature = scenario[1].toString();
            final Optional<String> allureId = pickle.getTags().stream()
                    .map(ID_TAG::matcher)
                    .filter(Matcher::matches)
                    .map(matcher -> matcher.group(ID_NAME))
                    .findFirst();

            final String selector = getSelector(pickle, feature);
            if (allureId.isPresent()) {
                final String id = allureId.get();
                return testPlan.getTests().stream()
                        .map(TestPlanV1_0.TestCase::getId)
                        .anyMatch(id::equals);
            } else {
                return testPlan.getTests().stream()
                        .map(TestPlanV1_0.TestCase::getSelector)
                        .anyMatch(selector::equals);
            }
        }
        return true;
    }

    private String getSelector(final Pickle pickle, final String feature) {
        final Matcher matcher = FEATURE.matcher(feature);
        final String name = matcher.matches() ? matcher.group("name") : "unknown";
        return String.format("%s: %s", name, pickle.getName());
    }
}
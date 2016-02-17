package fr.christopheml.objects;

import fr.christopheml.objects.samples.BusinessObject;
import fr.christopheml.objects.samples.SimpleObject;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests that {@link EqualsBuilder} adheres to the {@link Object#equals(Object)} contract.
 */
public class EqualsBuilderTest {

    @Test
    public void basic_test_for_reflexivity() {
        Object object = new Object();
        assertThat(EqualsBuilder.compare(object).with(object)).isTrue();
    }

    @Test
    public void test_for_reflexivity() {
        BusinessObject object = new BusinessObject("an object", 748, 983174.774d);
        boolean equals = EqualsBuilder.compare(object)
                .using(BusinessObject::getName)
                .using(BusinessObject::getCount)
                .using(BusinessObject::getRatio)
                .with(object);
        assertThat(equals).isTrue();
    }

    @Test
    public void test_for_symmetry() {
        SimpleObject o1 = new SimpleObject("object");
        SimpleObject o2 = new SimpleObject("object");

        boolean oneWayEquals = EqualsBuilder.compare(o1)
                .using(SimpleObject::getName)
                .with(o2);

        boolean otherWayEquals = EqualsBuilder.compare(o2)
                .using(SimpleObject::getName)
                .with(o1);

        assertThat(oneWayEquals).isEqualTo(otherWayEquals);
    }

    @Ignore
    @Test
    public void test_for_symmetry_subclass() {
        // FIXME Implement this test
    }

    @Test
    public void test_for_nullity() {
        assertThat(EqualsBuilder.compare(new Object()).with(null)).isFalse();
    }

}

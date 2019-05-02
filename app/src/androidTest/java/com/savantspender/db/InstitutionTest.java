package com.savantspender.db;


import com.plaid.client.response.Institution;
import com.savantspender.LiveDataTestUtil;
import com.savantspender.db.dao.InstitutionDao;
import com.savantspender.db.entity.InstitutionEntity;
import com.savantspender.db.entity.ItemEntity;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InstitutionTest extends DefaultDatabaseTest {
    private InstitutionDao mInstitution;

    @Before
    @Override
    public void createDb() {
        super.createDb();
        mInstitution = mDatabase.institutionDao();
    }

    @Test
    public void test_insert_read_delete() throws InterruptedException {
        //declare dumby varibles
        String id = "123456";
        String id2 = "234567";
        InstitutionEntity inputE = new InstitutionEntity(id, "BofA");
        InstitutionEntity expectedE = inputE;
        InstitutionEntity inputE2 = new InstitutionEntity(id2, "Chase");
        InstitutionEntity expectedE2 = inputE2;
        //populate database
        mInstitution.insert(inputE);
        mInstitution.insert(inputE2);

        //testing the getbyid method of the doa
        InstitutionEntity acutalE = LiveDataTestUtil.getValue(mInstitution.getById(id));
        assertThat(expectedE, is(instanceOf(InstitutionEntity.class)));
        assertThat(expectedE.id, is(acutalE.id));
        assertThat(expectedE.name, is(acutalE.name));

        InstitutionEntity acutalE2 = LiveDataTestUtil.getValue(mInstitution.getById(id2));
        assertThat(expectedE2.id, is(acutalE2.id));
        assertThat(expectedE2.name, is(acutalE2.name));

        //testing delete
        mInstitution.delete(inputE);
        InstitutionEntity nullE = LiveDataTestUtil.getValue(mInstitution.getById(id));
        assertThat(nullE, not(instanceOf(InstitutionEntity.class))); //checks if it returned and empty value



    }

    @Test
    public void preliminary_loader() throws InterruptedException
    {
        //declare dumby varibles
        String id = "123456";
        String id2 = "234567";
        InstitutionEntity inputE = new InstitutionEntity(id, "BofA");
        InstitutionEntity expectedE = inputE;
        InstitutionEntity inputE2 = new InstitutionEntity(id2, "Chase");
        InstitutionEntity expectedE2 = inputE2;
        //populate database
        mInstitution.insert(inputE);
        mInstitution.insert(inputE2);
        //testing the getbyid method of the doa
        InstitutionEntity acutalE = LiveDataTestUtil.getValue(mInstitution.getById(id));
        assertThat(expectedE, is(instanceOf(InstitutionEntity.class)));
        assertThat(expectedE.id, is(acutalE.id));
        assertThat(expectedE.name, is(acutalE.name));

        InstitutionEntity acutalE2 = LiveDataTestUtil.getValue(mInstitution.getById(id2));
        assertThat(expectedE2.id, is(acutalE2.id));
        assertThat(expectedE2.name, is(acutalE2.name));
    }

}

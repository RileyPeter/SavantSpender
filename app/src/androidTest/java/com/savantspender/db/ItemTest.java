package com.savantspender.db;

import com.savantspender.LiveDataTestUtil;
import com.savantspender.db.dao.ItemDao;
import com.savantspender.db.entity.ItemEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest extends InstitutionTest {
    private ItemDao mItems;

    @Before
    @Override
    public void createDb() {
        super.createDb();
        mItems = mDatabase.itemDao();
    }

    @Test
    public void test_insert_read_delete() throws InterruptedException {
        super.test_insert_read_delete();

        ItemEntity e = new ItemEntity("123456", "sandbox-123456", "instid-123", "public_blah");

        mItems.insert(e);

        //List<ItemEntity> entities = LiveDataTestUtil.getValue(mItems.getItems());

       // assertThat(entities.size(), is(1));

       // entities = LiveDataTestUtil.getValue(mItems.getItems());

       // assertThat(entities.size(), is(1));

       // ItemEntity retrieved = entities.get(0);

       // assertThat(retrieved, equalTo(e));

       // entities = LiveDataTestUtil.getValue(mItems.getItems());

       // assertThat(entities.size(), is(0));

      //  mItems.delete(retrieved);

        //entities = LiveDataTestUtil.getValue(mItems.getItems());

       // assertThat(entities.size(), is(0));
    }
}

package com.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.inventory.model.Item;
import com.inventory.model.User;
import com.inventory.repo.ItemRepository;
import com.inventory.repo.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired 
	private ItemRepository itemRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Test
	public void createItem() {
		Item item=new Item();
		item.setProduct("Notebook");
		item.setPriority("P1");
		item.setQuantity(1);
		
		Item savedItem=itemRepo.save(item);
		
		Item existItem=entityManager.find(Item.class, savedItem.getId());
		
		assertThat(existItem.getProduct().equals(item.getProduct()));
	}
	
}

package ru.atom.cache;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * What about Map?
 */
public class ContactListCache extends AbstractCache<Person, List<? extends Person>> {
    private final Map<Person, List<? extends Person>> cache = new HashMap<>(capacity);

    public ContactListCache(int capacity) {
        super(capacity);
    }

    @Override
    public boolean put(Person person, List<? extends Person> people) {
        if (cache.size() == capacity && !cache.containsKey(person)) {
            removeAny();
        }
        cache.put(person, people);
        return true;
    }


    @Override
    public List<? extends Person> get(Person person) {
        return cache.get(person);
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    private boolean removeAny() {
        Person remove = null;
        for (Person key : cache.keySet()) {
            remove = key;
            break;
        }
        cache.remove(remove);
        return true;
    }

}

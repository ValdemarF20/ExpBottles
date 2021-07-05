package net.arcticforestmc.expbottles.DataManagers;

import net.arcticforestmc.expbottles.Expbottles;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleDataContainer implements DataContainer {
    private final JavaPlugin mainInstance;

    public SimpleDataContainer(Expbottles expbottles){
        this.mainInstance = expbottles;
    }

    @Override
    public Object set(Object obj, String key, byte...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.BYTE_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Double value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.DOUBLE, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Float value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.FLOAT, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, int...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.INTEGER_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, long...args) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.LONG_ARRAY, args);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, Short value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.SHORT, value);
        }
        return obj;
    }

    @Override
    public Object set(Object obj, String key, String value) {
        if(obj instanceof PersistentDataHolder){
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();
            tagContainer.set(namespacedKey, PersistentDataType.STRING, value);
        }
        return obj;
    }

    @Override
    public String get(Object obj, final String key) {
        if(obj instanceof PersistentDataHolder) {
            PersistentDataHolder pdh = (PersistentDataHolder) obj;

            NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
            PersistentDataContainer tagContainer = pdh.getPersistentDataContainer();

            return tagContainer.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    @Override
    public boolean has(Object obj, String key) {
        PersistentDataHolder pdh = (PersistentDataHolder) obj;

        NamespacedKey namespacedKey = new NamespacedKey(mainInstance, key);
        PersistentDataContainer arrTagContainer = pdh.getPersistentDataContainer();

        if (arrTagContainer.has(namespacedKey, PersistentDataType.STRING)) {
            return true;
        }

        return false;
    }
}
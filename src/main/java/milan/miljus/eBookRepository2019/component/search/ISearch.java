package milan.miljus.eBookRepository2019.component.search;

import milan.miljus.eBookRepository2019.component.search.value.BaseIndexingObject;
import milan.miljus.eBookRepository2019.controller.exception.CustomException;

import java.util.List;

public interface ISearch<T extends BaseIndexingObject, E extends CustomException> {

    void addObject(T object) throws E;

    void addObjects(List<T> objects) throws E;

    void saveObject(T object) throws E;

    void saveObjects(List<T> objects) throws E;

    void partialUpdateObject(T object) throws E;

    void partialUpdateObjects(List<T> objects) throws E;

    void deleteObject(String objectId) throws E;

    void deleteObjects(List<String> objectIds) throws E;

}

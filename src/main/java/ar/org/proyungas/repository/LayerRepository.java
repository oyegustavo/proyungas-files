package ar.org.proyungas.repository;

import ar.org.proyungas.model.Layer;

public interface LayerRepository {
	
	Layer findById(Long layerId);

}

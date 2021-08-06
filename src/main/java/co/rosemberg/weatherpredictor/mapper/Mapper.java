package co.rosemberg.weatherpredictor.mapper;

public abstract class Mapper <E,D>{

    public abstract E domainToEntity(D domain);

    public abstract D entityToDomain(E entity);
}

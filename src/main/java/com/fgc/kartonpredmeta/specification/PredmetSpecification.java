package com.fgc.kartonpredmeta.specification;

import com.fgc.kartonpredmeta.dto.PredmetFilterDTO;
import com.fgc.kartonpredmeta.model.Angazovanje;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.model.PredmetModul;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class PredmetSpecification {

    public static Specification<Predmet> build(PredmetFilterDTO filter){

        Specification<Predmet> spec =
                (root, query, cb) -> cb.conjunction();



        if(filter.getNaziv() != null && !filter.getNaziv().isBlank()) {
            spec = spec.and(nazivContains(filter.getNaziv()));
        }

        if(filter.getGodinaStudija() != null) {
            spec = spec.and(
                    godinaStudijaEquals(filter.getGodinaStudija())
            );
        }
        
        if(filter.getSemestar() != null) {
            spec = spec.and(
                    semestarEquals(filter.getSemestar())
            );
        }

        if(filter.getEspb() != null) {
            spec = spec.and(
                    espbEquals(filter.getEspb())
            );
        }
        
        if(filter.getNastavnikId() != null) {
            spec = spec.and(
                    hasNastavnik(filter.getNastavnikId())
            );
        }

        if(filter.getModulId() != null) {
            spec = spec.and(
                    hasModul(filter.getModulId())
            );
        }
        
        return spec;
    }

    private static Specification<Predmet> hasModul(Long modulId) {
        return (root, query, cb) ->{
            query.distinct(true);

            Join<Predmet, PredmetModul> predmetModul=root.join("moduli");
            return cb.equal(predmetModul.get("modul").get("id"), modulId);
        };
    }

    private static Specification<Predmet> hasNastavnik(Long nastavnikId) {
        return (root, query, cb) ->{
            query.distinct(true);

            Join<Predmet, Angazovanje> angazovanje=root.join("angazovanja");
            return cb.equal(angazovanje.get("nastavnik").get("id"), nastavnikId);
        };
    }

    private static Specification<Predmet> espbEquals(Integer espb) {
        return (root, query, cb) ->
                cb.equal(root.get("espb"), espb);
    }

    private static Specification<Predmet> semestarEquals(Integer semestar) {
        return (root, query, cb) ->
                cb.equal(root.get("semestar"), semestar);
    }

    private static Specification<Predmet> godinaStudijaEquals(Integer godinaStudija) {
        return (root, query, cb) ->
                cb.equal(root.get("godinaStudija"), godinaStudija);
    }

    private static Specification<Predmet> nazivContains(String naziv) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("naziv")),
                        "%" + naziv.toLowerCase() + "%"
                );
    }


}

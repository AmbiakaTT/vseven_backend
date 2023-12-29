package com.vseven.launchpad.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
//@Setter
@AllArgsConstructor
public class Duo {
    Integer first;
    Integer second;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duo duo = (Duo) o;
        return first == duo.first && second == duo.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    
}

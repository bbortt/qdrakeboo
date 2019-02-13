package io.github.bbortt.tv.authorizationserver.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DublicateAwareHashSet<E> extends HashSet<E> implements Set<E>, Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public boolean add(E element) {
    return this.stream().filter(contained -> contained.equals(element)).findAny().isPresent()
        ? false
        : super.add(element);
  }
}

package io.github.bbortt.tv.core.authorizationserver.util;

import java.util.HashSet;
import java.util.Set;

public class DublicateCheckingSet<E> extends HashSet<E> implements Set<E> {

  private static final long serialVersionUID = 1L;

  @Override
  public boolean add(E element) {
    return this.stream().filter(contained -> contained.equals(element)).findAny().isPresent()
        ? false
        : super.add(element);
  }
}

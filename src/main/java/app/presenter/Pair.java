package app.presenter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class Pair<K, V> {
    private final K key;
    private final V value;
}

public final class RedisUserCache implements UserCache {

    @Override
    public void invalidate(String userId) {
        System.out.println("[Redis] кеш user:" + userId + " скинуто");
    }
}

// справжня реалізація кешу (поки просто друкує текст)

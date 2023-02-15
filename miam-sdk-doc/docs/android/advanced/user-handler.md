# User

Miam initialization process will start only after the user is **logged**.

Here is how to pass the user ID to the SDK, directly within the host app:

```kotlin
import com.miam.kmmMiamCore.handler.UserHandler

// Reference to your main "Miam" class
Miam.getInstance().UserHandler.updateUserId(USER_ID_IN_HOST_APP(string))
```

Here is how to inform the SDK whenever the user login state changes. We recommend using Observables
or EventListeners to that end.


```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

class Miam() {
  init {
    // CODE

    OBSERVABLE_ON_USER_OBJECT.collect { user ->
      UserHandler.updateUserId(<string>user.id)
    }
  }

  // CODE
}
```

You can disable Like feature with UserHandler

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

class Miam() {
  init {
    // CODE
    // false if you don't want like on recipe card
    UserHandler.setEnableLike(false)
  }

  // CODE
}
```

##### Profiling

You can block custom recipe suggestions if the user wishes.

```kotlin 
import com.miam.kmmMiamCore.handler.UserHandler

class Miam() {
  init {
    // CODE

    OBSERVABLE_ON_USER_PREF.collect { user ->
      UserHandler.setProfilingAllowed(<boolean>user.allowProfiling)
    }
  }

  // CODE
}

```
# Wiki.vg Data Generator
Gets packet data from [wiki.vg](https://wiki.vg/Protocol) and converts them to json format

---

## Installation

<img src="https://cdn.worldvectorlogo.com/logos/kotlin-2.svg" width="16px"></img>
**Kotlin DSL**
```kotlin
repositories {
    maven {
        name = "devOS"
        url = uri("https://mvn.devos.one/releases")
    }
}

dependencies {
    implementation("io.github.dockyardmc:wikivg-datagen:1.2")
}
```
<img src="https://github.com/LukynkaCZE/PrettyLog/assets/48604271/3293feca-7395-4100-8b61-257ba40dbe3c" width="18px"></img>
**Gradle Groovy**
```groovy
repositories {
  maven {
    name "devOS"
    url "https://mvn.devos.one/releases"
  }
}

dependencies {
  implementation 'io.github.dockyardmc:wikivg-datagen:1.2'
}
```
---

## Usage

You can get the latest data like this:

```kotlin
val dataGenerator = WikiVGDataGenerator()
val json = dataGenerator.json
val list = dataGenerator.packets
```

**Example:** _(Sound Effect Packet)_

```json
{
  "id": "0x68",
  "header": "Sound Effect",
  "packet": "sound_effect",
  "protocol_state": "play",
  "bound_to": "client",
  "content": [
    {
        "field": "sound_name",
        "type": "optional_identifier"
    },
    {
        "field": "has_fixed_range",
        "type": "optional_boolean"
    },
    {
        "field": "range",
        "type": "optional_float"
    },
    {
        "field": "sound_category",
        "type": "var_int_enum"
    },
    {
        "field": "effect_position_x",
        "type": "int"
    },
    {
        "field": "effect_position_y",
        "type": "int"
    },
    {
        "field": "effect_position_z",
        "type": "int"
    },
    {
        "field": "volume",
        "type": "float"
    },
    {
        "field": "pitch",
        "type": "float"
    },
    {
        "field": "seed",
        "type": "long"
    }
  ]
}
```
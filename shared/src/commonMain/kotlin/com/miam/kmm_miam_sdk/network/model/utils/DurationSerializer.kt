package com.miam.kmm_miam_sdk.network.model.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object DurationSerializer : KSerializer<Duration> {
    override fun deserialize(decoder: Decoder): Duration =
        Duration.parseIsoString(decoder.decodeString())


    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Duration",PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Duration) {
         encoder.encodeString(value.toIsoString())
    }
}
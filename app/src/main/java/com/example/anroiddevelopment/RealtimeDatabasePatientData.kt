package com.example.anroiddevelopment

// Model class used to store one patient record in Firebase.
data class RealtimeDatabasePatientData(

    // Firebase unique key (used for Update/Delete).
    var patientId: String? = null,

    // Drug name.
    var patientName: String? = null,

    // Drug description.
    var patientDiscription: String? = null,

    // Side effects.
    var patientSideEffect: String? = null,

    // Prevention.
    var patientPrevention: String? = null,

    // Treatment.
    var patientTreatment: String? = null
)
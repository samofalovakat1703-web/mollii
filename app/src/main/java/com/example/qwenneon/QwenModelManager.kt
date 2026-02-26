// QwenModelManager.kt

package com.example.qwenneon

class QwenModelManager {
    private var model: QwenModel? = null

    fun initializeModel(modelPath: String) {
        model = QwenModel(modelPath)
    }

    fun infer(data: InputData): OutputData? {
        return model?.predict(data)
    }
}
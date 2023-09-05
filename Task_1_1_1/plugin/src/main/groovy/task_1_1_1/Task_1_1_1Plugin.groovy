/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package task_1_1_1

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * A simple 'hello world' plugin.
 */
class Task_1_1_1Plugin implements Plugin<Project> {
    void apply(Project project) {
        // Register a task
        project.tasks.register("greeting") {
            doLast {
                println("Hello from plugin 'task_1_1_1.greeting'")
            }
        }
    }
}

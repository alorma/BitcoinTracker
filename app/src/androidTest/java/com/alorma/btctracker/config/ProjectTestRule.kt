package com.alorma.btctracker.config

import android.app.Activity
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ProjectTestRule<T : Activity>(activityClass: Class<T>, private val target: Any) : TestRule {

    private val daggerRule = getDaggerRule()
    private val activityRule = ActivityTestRule<T>(activityClass, false, false)

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain.outerRule(activityRule)
                .around { newBase, _ -> daggerRule.apply(newBase, null, target) }
                .apply(base, description)
    }

    fun run() = activityRule.launchActivity(null)
    fun run(intent: Intent) = activityRule.launchActivity(intent)
}